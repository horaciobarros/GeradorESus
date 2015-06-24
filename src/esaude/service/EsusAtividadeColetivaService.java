package esaude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

import br.gov.saude.esus.cds.transport.generated.thrift.atividadecoletiva.FichaAtividadeColetivaThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadecoletiva.ParticipanteRowItemThrift;
import br.gov.saude.esus.cds.transport.generated.thrift.atividadecoletiva.ProfissionalCboRowItemThrift;
import br.gov.saude.esus.transport.common.generated.thrift.DadoTransporteThrift;
import esaude.dao.EsusAtividadeColetivaDao;
import esaude.model.EsusAtividadeColetiva;
import esaude.model.EsusAtividadeColetivaParticipantes;
import esaude.model.EsusAtividadeColetivaProfissional;
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
			esusRegistro = esusRegistroService.buscaEsusRegistro();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Esus registro n�o encontrado");
			throw e;
		}

		log.info(new Date() + " -- Gerando Atividade Coletiva  -------");
		TelaPrincipal.enviaLog(new Date()
				+ " -- Gerando Atividade Coletiva  -------");

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
					informacoesEnvioDto.setIneDadoSerializado(cad
							.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					// Passo 4: preencher o thrift de transporte com as
					// informadosçõeso
					// coletadas;
					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);
					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

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

	private FichaAtividadeColetivaThrift converterParaThrift(
			EsusAtividadeColetiva cad) {
		FichaAtividadeColetivaThrift ficha = new FichaAtividadeColetivaThrift();
		List<EsusAtividadeColetivaParticipantes> participantes = dao
				.findParticipantes(cad.getId());
		List<ParticipanteRowItemThrift> participantesThrift = converteParticipantes(participantes);
		ficha.setParticipantes(participantesThrift);

		List<EsusAtividadeColetivaProfissional> profissionais = dao
				.findProfissionais(cad.getId());
		List<ProfissionalCboRowItemThrift> profissionaisThrift = converteProfissionais(profissionais);
		ficha.setProfissionais(profissionaisThrift);

		ficha.setCodigoIbgeMunicipio(cad.getIbgeMunicipio());
		try {
			ficha.setAtividadeTipo(cad.getEsusTipoatividadecoletiva().getId());
		} catch (Exception e) {

		}

		ficha.setAtividadeTipoIsSet(true);
		ficha.setUuidFicha(cad.getId().toString());
		try {
			ficha.setNumParticipantesProgramados(Integer.parseInt(cad
					.getNumParticipantesProgramados().toString()));
		} catch (Exception e) {

		}
		try {
			ficha.setNumAvaliacoesAlteradas(Integer.parseInt(cad
					.getNumAvaliacoes().toString()));
		} catch (Exception e) {

		}

		try {

			ficha.setResponsavelCnesUnidade(cad.getCnsResponsavel());
			ficha.setCodigoIbgeMunicipio(cad.getIbgeMunicipio());
			ficha.setCodigoIbgeMunicipioIsSet(true);
			ficha.setDtAtividadeColetiva(cad.getDtAtividade().getTime());
			ficha.setDtAtividadeColetivaIsSet(true);
			ficha.setInep(cad.getInep());
			ficha.setInepIsSet(true);
			ficha.setLocalAtividade(cad.getLocalAtividade());
			ficha.setLocalAtividadeIsSet(true);
			ficha.setResponsavelCnesUnidadeIsSet(true);
		} catch (Exception e) {
		}

		return ficha;
	}

	private List<ProfissionalCboRowItemThrift> converteProfissionais(
			List<EsusAtividadeColetivaProfissional> profissionais) {
		List<ProfissionalCboRowItemThrift> lista = new ArrayList<ProfissionalCboRowItemThrift>();
		for (EsusAtividadeColetivaProfissional prof : profissionais) {
			ProfissionalCboRowItemThrift item = new ProfissionalCboRowItemThrift();
			item.setCns(prof.getCnsProfissional());
			item.setCnsIsSet(true);
			item.setCodigoCbo2002(prof.getCbo());
			item.setCodigoCbo2002IsSet(true);
			lista.add(item);
		}

		return lista;
	}

	private List<ParticipanteRowItemThrift> converteParticipantes(
			List<EsusAtividadeColetivaParticipantes> participantes) {
		List<ParticipanteRowItemThrift> lista = new ArrayList<ParticipanteRowItemThrift>();
		for (EsusAtividadeColetivaParticipantes part : participantes) {
			ParticipanteRowItemThrift item = new ParticipanteRowItemThrift();
			item.setAbandonouGrupo(part.getAbandonouGrupo());
			item.setAbandonouGrupoIsSet(true);
			item.setAltura(part.getAltura());
			item.setAlturaIsSet(true);
			item.setAvaliacaoAlterada(part.getAvaliacaoAlterada());
			item.setAvaliacaoAlteradaIsSet(true);
			item.setCessouHabitoFumar(part.getCessouHabitoFumar());
			item.setCessouHabitoFumarIsSet(true);
			item.setCns(part.getPProntuario().getCertidaoNova()); // obrigat�rio
			item.setCnsIsSet(true);
			lista.add(item);
		}
		return lista;
	}

}
