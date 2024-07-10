package com.sistema.desktop_cr7_imports.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

import com.sistema.desktop_cr7_imports.model.User;

public class AboutScreenService {

	public LinkedHashMap<String, Object> getInfos() throws UnknownHostException {
		LinkedHashMap<String, Object> infos = new LinkedHashMap<String, Object>();
		infos.put("Usuario Aplicação", User.getInstance().getUser());
		infos.put("IP PC", InetAddress.getLocalHost());
		infos.put("Sistema Operacional", System.getProperty("os.name"));
		infos.put("Versão Java", System.getProperty("java.version"));
		infos.put("Usuario PC", System.getProperty("user.name"));
		infos.put("Versão JRE", System.getProperty("java.runtime.version"));
		infos.put("Arq. Sistema Operacional", System.getProperty("os.arch"));
		infos.put("Vendor Java", System.getProperty("java.vm.specification.vendor"));
		infos.put("Pais Uso Aplicação", System.getProperty("user.country"));
		infos.put("Diretorio Aplicação", System.getProperty("user.dir"));
		infos.put("Diretorio Java", System.getProperty("java.home"));
		return infos;
	}

}
