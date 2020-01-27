package arsw.threads;

public class RegistroLlegada {

	private int ultimaPosicionAlcanzada=1;

	private String ganador=null;
	
	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public synchronized int  getUltimaPosicionAlcanzada() {
		return ultimaPosicionAlcanzada;
	}

	public synchronized int  getUltimaPosicionAlcanzadaGalgo() {
		this.setUltimaPosicionAlcanzada(ultimaPosicionAlcanzada + 1 );
		return ultimaPosicionAlcanzada-1;
	}

	public void setUltimaPosicionAlcanzada(int ultimaPosicionAlcanzada) {
		this.ultimaPosicionAlcanzada = ultimaPosicionAlcanzada;
	}

	
	
}
