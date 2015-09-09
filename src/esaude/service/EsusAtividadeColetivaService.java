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
import esaude.model.EsusAtividadeColetivaPublico;
import esaude.model.EsusAtividadeColetivaTemas;
import esaude.model.EsusRegistro;
import esaude.model.EsusTemasparareuniao;
import esaude.model.SisRegistro;
import esaude.util.InformacoesEnvio;
import esaude.util.InformacoesEnvioDto;
import esaude.util.ThriftSerializer;
import esaude.view.TelaPrincipal;

public class EsusAtividadeColetivaService {
	static Logger log = Logger.getLogger(EsusCadastroDomiciliarService.class
			.getName());
	private EsusAtividadeColetivaDao dao = new EsusAtividadeColetivaDao();
	private EsusRegistro esusRegistro = new EsusRegistro();
	private SisRegistro sisRegistro = new SisRegistro();
	private MasterService masterService = new MasterService();

	public List<EsusAtividadeColetiva> findNaoEnvidados() {
		return dao.findNaoEnviados();
	}

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
			JOptionPane.showMessageDialog(null, "Sis registro não encontrado");
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

					// Passo 3: coletar as informaÃ§Ãµes do envio
					informacoesEnvioDto.setTipoDadoSerializado(6L); // importante,
																	// aqui
																	// identifica
																	// qual tipo
																	// de ficha
																	// está
																	// sendo
																	// enviado
					informacoesEnvioDto.setDadoSerializado(dadoSerializado);
					informacoesEnvioDto.setUuidDadoSerializado(thriftAtividadeColetiva.getUuidFicha());
					informacoesEnvioDto.setIneDadoSerializado(cad
							.getIneEquipe());
					informacoesEnvioDto.setCnesDadoSerializado(cad
							.getCnesUnidade());

					DadoTransporteThrift dadoTransporteThrift = InformacoesEnvio
							.getInfoInstalacao(informacoesEnvioDto,
									esusRegistro);

					dados.add(dadoTransporteThrift);
					cad.setDtEnvio(new Date());
					cad.setStEnvio(Long.valueOf(1));
					dao.atualiza(cad);

					log.info(new Date()
							+ " -- Gerando atividade coletiva  --> "
							+ cad.getId() + " - " + thriftAtividadeColetiva.getUuidFicha());
					System.out.println("Gerando atividade coletiva --> "
							+ cad.getId());

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

		ficha.setUuidFicha(masterService.gerarUuid(cad.getCnesUnidade()));
		ficha.setUuidFichaIsSet(true);
		cad.setUuid(ficha.getUuidFicha());

		try {
			List<EsusAtividadeColetivaParticipantes> participantes = dao
					.findParticipantes(cad.getId());
			List<ParticipanteRowItemThrift> participantesThrift = converteParticipantes(participantes);
			ficha.setParticipantes(participantesThrift);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		try {
			List<EsusAtividadeColetivaProfissional> profissionais = dao
					.findProfissionais(cad.getId());
			List<ProfissionalCboRowItemThrift> profissionaisThrift = converteProfissionais(profissionais);
			ficha.setProfissionais(profissionaisThrift);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		try {

			List<EsusAtividadeColetivaTemas> atividadeColetivaTemas = dao
					.findTemas(cad.getId());
			List<Long> temasParaReuniao = converteTemas(atividadeColetivaTemas);
			ficha.setTemasParaReuniao(temasParaReuniao);
			ficha.setTemasParaReuniaoIsSet(temasParaReuniao != null
					&& temasParaReuniao.size() > 0);
			ficha.setTemasParaReuniao(temasParaReuniao);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		try {
			List<EsusAtividadeColetivaPublico> atividadeColetivaPublico = dao
					.findPublico(cad.getId());
			List<Long> publico = convertePublico(atividadeColetivaPublico);
			ficha.setPublicoAlvo(publico);
			ficha.setPublicoAlvoIsSet(publico != null && publico.size() > 0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		try {
			ficha.setAtividadeTipo(cad.getEsusTipoatividadecoletiva().getId());
			ficha.setAtividadeTipoIsSet(true);
		} catch (Exception e) {

		}

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
			ficha.setResponsavelCnesUnidade(cad.getCnesUnidade());
			ficha.setResponsavelCnesUnidadeIsSet(true);
			ficha.setCodigoIbgeMunicipioIsSet(true);
			ficha.setCodigoIbgeMunicipio(sisRegistro.getCidadeIbge());
			ficha.setDtAtividadeColetiva(cad.getDtAtividade().getTime());
			ficha.setDtAtividadeColetivaIsSet(true);
			ficha.setInep(cad.getInep());
			ficha.setInepIsSet(true);
			ficha.setLocalAtividade(cad.getLocalAtividade());
			ficha.setLocalAtividadeIsSet(true);
			ficha.setTbCdsOrigem(3);
			ficha.setTbCdsOrigemIsSet(true);
			ficha.setResponsavelCns(cad.getCnsResponsavel());
			ficha.setResponsavelCnsIsSet(true);
			ficha.setResponsavelNumIne(cad.getIneEquipe());
			ficha.setResponsavelNumIneIsSet(true);
			
		} catch (Exception e) {

		}

		return ficha;
	}

	private List<Long> convertePublico(
			List<EsusAtividadeColetivaPublico> atividadeColetivaPublico) {
		List<Long> lista = new ArrayList<Long>();
		for (EsusAtividadeColetivaPublico ap : atividadeColetivaPublico) {
			lista.add(ap.getEsusPublicoalvo().getId());
		}

		return lista;

	}

	private List<Long> converteTemas(
			List<EsusAtividadeColetivaTemas> atividadeColetivaTemas) {

		List<Long> lista = new ArrayList<Long>();
		for (EsusAtividadeColetivaTemas at : atividadeColetivaTemas) {
			lista.add(at.getEsusTemasparareuniao().getId());
		}
		return lista;
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
			item.setAvaliacaoAlterada(part.getAvaliacaoAlterada());
			item.setAvaliacaoAlteradaIsSet(true);
			item.setCessouHabitoFumar(part.getCessouHabitoFumar());
			item.setCessouHabitoFumarIsSet(true);
			item.setCns(part.getPProntuario().getCertidaoNova()); // obrigatório
			item.setCnsIsSet(true);
			lista.add(item);
		}
		return lista;
	}

}
