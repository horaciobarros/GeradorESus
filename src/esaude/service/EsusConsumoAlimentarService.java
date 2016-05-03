package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.consumoalimentar.FichaConsumoAlimentarThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.model.EsusConsumoAlimentar;
import esaude.model.EsusRegistro;
import esaude.model.SisRegistro;
import esaude.view.TelaPrincipal;

/**
 * @author Fernando Werneck - 02/05/2016 Analista Desenvolvedor
 *         fernandowtb@hotmail.com www.jwaysistemas.com.br (31) 98594-8242
 */
public class EsusConsumoAlimentarService extends MasterService {
	static Logger log = Logger.getLogger(EsusConsumoAlimentarService.class
			.getName());

	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<DadoTransporteThrift> buscaRegistros() {
		EsusRegistroServiceImpl esusRegistroService = new EsusRegistroServiceImpl();
		try {
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esus registro não encontrado");
			throw e;
		}
		SisRegistroService sisRegistroService = new SisRegistroService();
		try {
			sisRegistro = sisRegistroService.buscaSisRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sisregistro não encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Consumo Alimentar -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Consumo Alimentar -------");

		List<DadoTransporteThrift> dados = new ArrayList<DadoTransporteThrift>();
		try {

		} catch (JDBCConnectionException e) {
			log.error(new Date() + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dados;
	}
	
	private FichaConsumoAlimentarThrift converterParaThrift(EsusConsumoAlimentar cad) {
		FichaConsumoAlimentarThrift c = new FichaConsumoAlimentarThrift();
		
		try {
			c.setUuidFichaIsSet(true);
			c.setUuidFicha(masterService.gerarUuid(cad.getCnesUnidade()));
			cad.setUuid(c.getUuidFicha());

		} catch (Exception e) {

		}

		try {
			c.setTpCdsOrigem(3);
			c.setTpCdsOrigemIsSet(true);
		} catch (Exception e) {

		}
		
		try {
			UnicaLotacaoHeaderThrift unicaLotacao = new UnicaLotacaoHeaderThrift();
			unicaLotacao.setCboCodigo_2002(cad.getCboProfissional());
			unicaLotacao.setCboCodigo_2002IsSet(true);
			unicaLotacao.setCnes(cad.getCnesUnidade());
			unicaLotacao.setCnesIsSet(true);
			unicaLotacao.setProfissionalCNS(cad.getCnsProfissional());
			unicaLotacao.setProfissionalCNSIsSet(true);
			try {
				unicaLotacao.setDataAtendimento(cad.getDtAtendimento()
						.getTime());
				unicaLotacao.setDataAtendimentoIsSet(true);
			} catch (Exception e) {
				log.error("Data de atendimento inválida ou nula. Id:"
						+ cad.getId() + " -- " + e.getMessage());
				e.printStackTrace();
			}
			unicaLotacao.setCodigoIbgeMunicipio(sisRegistro.getCidadeIbge());
			unicaLotacao.setCodigoIbgeMunicipioIsSet(true);
			c.setHeaderTransport(unicaLotacao);
		} catch (Exception e) {

		}
		
		try{
			c.setDataNascimento(cad.getDtNascimento().getTime());
			c.setDataNascimentoIsSet(true);
		}
		catch(Exception e){
			
		}
		
		
		return c;
	}

}
