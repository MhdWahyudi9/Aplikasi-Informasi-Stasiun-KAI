package com.yudi.aplikasiinformasistasiunkai.Model;

import com.google.gson.annotations.SerializedName;

public class StasiunKAI {

	@SerializedName("kota")
	private String kota;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("luas")
	private Integer luas;

	@SerializedName("sejarah")
	private String sejarah;

	@SerializedName("id")
	private Integer id;

	public String getKota(){
		return kota;
	}

	public String getNama(){
		return nama;
	}

	public String getFoto(){
		return foto;
	}

	public Integer getLuas(){
		return luas;
	}

	public String getSejarah(){
		return sejarah;
	}

	public Integer getId(){
		return id;
	}
}