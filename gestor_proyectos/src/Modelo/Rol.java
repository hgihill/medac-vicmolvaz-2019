package Modelo;

public class Rol {
	private boolean bCandidato, bParticipante, bCreador;
	private Usuario oUs;
	private Proyecto oProyecto;

	public Rol(boolean bCandidato, boolean bParticipante, boolean bCreado, Usuario oUs, Proyecto oProyecto) {
		setbCandidato(bCandidato);
		setbParticipante(bParticipante);
		setbCreador(bCreado);
		setoUs(oUs);
		setoProyecto(oProyecto);
	}

	public Rol(Usuario oUs, Proyecto oProyecto) {
		setoUs(oUs);
		setoProyecto(oProyecto);
	}

	public boolean isbCandidato() {
		return bCandidato;
	}

	public void setbCandidato(boolean bCandidato) {
		this.bCandidato = bCandidato;
	}

	public boolean isbParticipante() {
		return bParticipante;
	}

	public void setbParticipante(boolean bParticipante) {
		this.bParticipante = bParticipante;
	}

	public boolean isbCreador() {
		return bCreador;
	}

	public void setbCreador(boolean bCreador) {
		this.bCreador = bCreador;
	}

	public Usuario getoUs() {
		return oUs;
	}

	private void setoUs(Usuario oUs) {
		this.oUs = oUs;
	}

	public Proyecto getoProyecto() {
		return oProyecto;
	}

	private void setoProyecto(Proyecto oProyecto) {
		this.oProyecto = oProyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bCandidato ? 1231 : 1237);
		result = prime * result + (bCreador ? 1231 : 1237);
		result = prime * result + (bParticipante ? 1231 : 1237);
		result = prime * result + ((oProyecto == null) ? 0 : oProyecto.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bExito = false;
		Rol otro = (Rol) obj;
		if (this != null && otro != null && this.getoUs() == (otro.getoUs())
				&& this.getoProyecto() == (otro.getoProyecto())) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String toString() {
		String sResultado = "";
		sResultado += "Creador: " + isbCreador() + "\n";
		sResultado += "Participante: " + isbParticipante() + "\n";
		sResultado += "Candidato: " + isbCandidato() + "\n";
		sResultado += "Usuario: " + getoUs() + "\n";
		sResultado += "Proyecto asociado: " + getoProyecto() + "\n";
		return sResultado;
	}
}
