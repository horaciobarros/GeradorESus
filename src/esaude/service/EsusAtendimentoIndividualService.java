package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.atividadeindividual.FichaAtendimentoIndividualMasterThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusAtendimentoIndividualDao;
import esaude.model.EsusAtendimentoIndividual;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusAtendimentoIndividualService {
	static Logger log = Logger.getLogger(EsusAtendimentoIndividualService.class
			.getName());
	private EsusAtendimentoIndividualDao dao = new EsusAtendimentoIndividualDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();

	public List<EsusAtendimentoIndividual> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {

		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esus registro n�o encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro n�o encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Atendimento Individual -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Atendimento individual -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusAtendimentoIndividual cad : dao.findNaoEnviados()) {
				try {
					FichaAtendimentoIndividualMasterThrift thriftAtendimentoIndividual = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer
							.serialize(thriftAtendimentoIndividual);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(4l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(cad.getId()
							.toString());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);
					
					log.info(new Date() + " -- Gerando cadastro Individual --> "
							+ cad.getId() + " - " + cad.getId());
					System.out.println("Gerando cadastro Individual --> " + cad.getId()
							);


					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

					System.out.println("Cadastro individual:" + cad.getId());

				} catch (JDBCConnectionException e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getMessage());
				} catch (Exception e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date() + " - " + e.getMessage());
				}
			}

		} catch (JDBCConnectionException e) {
			log.error(new Date() + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dados;
	}

	private FichaAtendimentoIndividualMasterThrift converterParaThrift(
			EsusAtendimentoIndividual cad) {
		FichaAtendimentoIndividualMasterThrift c = new FichaAtendimentoIndividualMasterThrift();

		c.setUuidFicha(cad.getId().toString());
		c.setUuidFichaIsSet(true);

		c.setTpCdsOrigem(3);
		c.setTpCdsOrigemIsSet(true);

		return c;
	}


}
