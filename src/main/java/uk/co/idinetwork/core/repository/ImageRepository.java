package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.MultiPartFile;

public interface ImageRepository {
	public List<MultiPartFile> loadAllImages();
}
