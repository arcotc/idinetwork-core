package uk.co.idinetwork.core.repository;

import java.util.ArrayList;
import java.util.List;

import uk.co.idinetwork.core.model.MultiPartFile;

public class ImageRepositoryImpl implements ImageRepository {

	@Override
	public List<MultiPartFile> loadAllImages() {
		// TODO: load images from database
		return new ArrayList<MultiPartFile>();
	}

}
