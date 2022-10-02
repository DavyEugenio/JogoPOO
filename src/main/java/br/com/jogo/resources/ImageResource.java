package br.com.jogo.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jogo.security.exceptions.AuthorizationException;
import br.com.jogo.services.ImageService;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@RestController
public class ImageResource {

	@Value("${img.local.storage}")
	private String localStorage;

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/imagens/{filename}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException,FileNotFoundException,ObjectNotFoundException,AuthorizationException {
		String prefix = filename.substring(0, 3);
		if (prefix.equals("jog")) {
			File imgFile = imageService.findImage(filename);
			byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(imgFile));
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} else {
			throw new AuthorizationException("Acesso negado!");
		}
	}
}
