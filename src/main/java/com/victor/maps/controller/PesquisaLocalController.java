package com.victor.maps.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.api.staticmap.v1.MapboxStaticMap;
import com.mapbox.geojson.Point;

import okhttp3.HttpUrl;
import retrofit2.Response;

public class PesquisaLocalController {
	
	private static final String ACCESS_TOKEN = "pk.eyJ1Ijoiamh1bGx5bXVsbGVyMDYiLCJhIjoiY2ptZTB2bnU5MGk0ZjNrbXphczIyeXNmeSJ9.CDRqiee1oFRxWaeLmRxyYg";

	private static Point buscaCoordenada(String pesquisa) throws IOException {
		//https://www.mapbox.com/api-documentation/?language=Java#search-for-places
		
		//Cria a busca
		MapboxGeocoding client = MapboxGeocoding.builder()
				  .accessToken(ACCESS_TOKEN)
				  .query(pesquisa)
				  .build();
		
		//Chama a url criada pela busca na API (servidor) do MapBox
		//https://api.mapbox.com/geocoding/v5/mapbox.places/pesquisa.json?access_token=pk.eyJ1Ijoiamh1bGx5bXVsbGVyMDYiLCJhIjoiY2ptZTB2bnU5MGk0ZjNrbXphczIyeXNmeSJ9.CDRqiee1oFRxWaeLmRxyYg
		Response<GeocodingResponse> response = client.executeCall();
		
		//Retorna as coordenadas respondida pela API do MapBox
		//Features é a lista de locais retornados
		//Center é a coordenada
		return response.body().features().get(0).center();
	}

	public static BufferedImage buscaImagemMapa(String pesquisa) throws IOException {
		Point coordenada = buscaCoordenada(pesquisa);
		System.out.println("Coordenadas: "+ coordenada);
		
		
		//Com a coordenada é gerada a url que buscará a imagem na API do MapBox
		HttpUrl url = MapboxStaticMap.builder()
				  .accessToken(ACCESS_TOKEN)
				  .styleId("streets-v10")
				  .cameraPoint(coordenada)
				  .cameraZoom(14) //zoom: quanto menor maior a imagem do local
				  .cameraPitch(0)
				  .cameraBearing(20)
				  .width(300)
				  .height(300)
				  .retina(true)
				  .build().url();
		//https://api.mapbox.com/styles/v1/mapbox/streets-v10/static/-48.549600,-27.597300,12.000000,20.000000,0.000000/300x300@2x?access_token=pk.eyJ1Ijoiamh1bGx5bXVsbGVyMDYiLCJhIjoiY2ptZTB2bnU5MGk0ZjNrbXphczIyeXNmeSJ9.CDRqiee1oFRxWaeLmRxyYg
		System.out.println("URL: "+ url);
		
		//Chama a API do MapBox que retorna uma imagem do mapa do local pesquisado
		return ImageIO.read(url.url());
	}
	public static BufferedImage buscaImagemMapa(String pesquisa, int zoom) throws IOException {
			Point coordenada = buscaCoordenada(pesquisa);
			System.out.println("Coordenadas: "+ coordenada);
			
			
			//Com a coordenada é gerada a url que buscará a imagem na API do MapBox
			HttpUrl url = MapboxStaticMap.builder()
					  .accessToken(ACCESS_TOKEN)
					  .styleId("streets-v10")
					  .cameraPoint(coordenada)
					  .cameraZoom(zoom) //zoom: quanto menor maior a imagem do local
					  .cameraPitch(0)
					  .cameraBearing(20)
					  .width(300)
					  .height(300)
					  .retina(true)
					  .build().url();
			//https://api.mapbox.com/styles/v1/mapbox/streets-v10/static/-48.549600,-27.597300,12.000000,20.000000,0.000000/300x300@2x?access_token=pk.eyJ1Ijoiamh1bGx5bXVsbGVyMDYiLCJhIjoiY2ptZTB2bnU5MGk0ZjNrbXphczIyeXNmeSJ9.CDRqiee1oFRxWaeLmRxyYg
			System.out.println("URL: "+ url);
			
			//Chama a API do MapBox que retorna uma imagem do mapa do local pesquisado
			return ImageIO.read(url.url());
	}

	
	
}
