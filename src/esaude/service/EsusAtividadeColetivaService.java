package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.atividadecoletiva.FichaAtividadeColetivaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.EnderecoLocalPermanenciaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.cadastrodomiciliar.FamiliaRowThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.common.HeaderCdsCadastroThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusAtividadeColetivaDao;
import esaude.model.EsusAtividadeColetiva;
import esaude.model.EsusRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusAtividadeColetivaService {
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());
	private EsusAtividadeColetivaDao dao = new EsusAtividadeColetivaDao();
	private EsusRegistro esusRegistro = new EsusRegistro();

	public List<EsusAtividadeColetiva> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

	public List<DadoTransporteThrift> buscaRegistros() {
		
		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService
					.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Esus registro n�o encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Cadastro domiciliar -------");
		TelaPrincipal.enviaLog(new Date() + " -- Gerando Cadastro domiciliar -------");
		
		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

			for (EsusAtividadeColetiva cad : dao.findNaoEnviados()) {
				try {
					FichaAtividadeColetivaThrift thriftAtividadeColetiva = converterParaThrift(cad);

					InformacoesEnvioDto informacoesEnvioDto = new InformacoesEnvioDto();

					byte[] dadoSerializado;

					// Passo 2: serializar o thrift
					dadoSerializado = ThriftSerializer
							.serialize(thriftAtividadeColetiva);

					// Passo 3: coletar as informações do envio
					informacoesEnvioDto.setTipoDadoSerializado(7l);
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(cad.getId()
							.toString());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto, esusRegistro);
					
					dados.add(dadoTransporteThrift);
					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

				} catch (JDBCConnectionException e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date()+" - "+e.getMessage());
				} catch (Exception e) {
					log.info(e.getMessage());
					e.printStackTrace();
					TelaPrincipal.enviaLog(new Date()+" - "+e.getMessage());
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

	private FichaAtividadeColetivaThrift converterParaThrift(
			EsusAtividadeColetiva cad) {
		FichaAtividadeColetivaThrift fichaAtividadeColetivaThrift = new FichaAtividadeColetivaThrift();



		return fichaAtividadeColetivaThrift;
	}
	
}
