package br.com.jogo.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.jogo.services.exceptions.FileException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class ImageService {
	@Value("${img.local.storage}")
	private String localStorage;
	@Value("${host.url}")
	private String hostUrl;
	@Value("${img.size}")
	private Integer size;

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equalsIgnoreCase(ext) && !"jpg".equalsIgnoreCase(ext) && !"jpeg".equalsIgnoreCase(ext)) {
			throw new FileException("Somente imagens JPG e PNG são permitidas!");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equalsIgnoreCase(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo!");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo!");
		}
	}

	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(sourceImg, (sourceImg.getWidth() / 2) - (min / 2), (sourceImg.getHeight() / 2) - (min / 2),
				min, min);
	}

	public BufferedImage resize(BufferedImage sourceImg) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}

	public File findImage(String fileName) {
		String caminho = localStorage + "/" + fileName;
		File file = new File(caminho);
		if (file.exists()) {
			return file;
		} else {
			throw new ObjectNotFoundException("Imagem não existente no sistema");
		}

	}

	public URI uploadImage(InputStream img, String fileName) {
		String caminho = localStorage + "/" + fileName;
		File file = new File(caminho);
		try {
			FileUtils.copyInputStreamToFile(img, file);
		} catch (IOException e) {
			throw new FileException("Erro ao salvar a imagem");
		}
		return URI.create(hostUrl + "/imagens/" + fileName);
	}

	public void deleteImage(String fileName) throws ObjectNotFoundException {
		findImage(fileName);
		File file = findImage(fileName);
		file.delete();
	}
}
