package uk.co.idinetwork.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.MultiPartFile;
import uk.co.idinetwork.core.repository.ImageRepository;

public class ImageServiceImpl implements ImageService {
	@Autowired ImageRepository imageRepository;
	
	@Override
	public List<MultiPartFile> loadAllImages() {
		return imageRepository.loadAllImages();
	}

}
