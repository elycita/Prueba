package heladeria.bambi.dao;

import java.util.HashMap;
import java.util.Map;

public class P {

	private Map<String, Object> parametros;

	private P() {
		this.parametros = new HashMap<String, Object>();
	}

	public void put(String nombre, Object valor) {
		parametros.put(nombre, valor);
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public static P getP() {
		return new P();
	}

}
