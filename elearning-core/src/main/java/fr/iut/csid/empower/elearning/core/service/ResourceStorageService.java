package fr.iut.csid.empower.elearning.core.service;

import java.io.InputStream;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * Service de requetage du repo MongoDB stockant les ressources physiques
 */
public interface ResourceStorageService {

	/**
	 * Retourne la ressource correspondnate
	 * 
	 * @param resourceName
	 * @return
	 */
	public GridFSDBFile getByResourceName(String resourceName);

	/**
	 * Retourne toutes les ressources
	 * 
	 * @return
	 */
	public List<GridFSDBFile> listResources();

	/**
	 * Retourne la ressource correspondante à l'id
	 * 
	 * @param id
	 * @return
	 */
	public GridFSDBFile get(String id);

	/**
	 * Sauvegarde une ressource
	 * 
	 * @param inputStream
	 * @param contentType
	 * @param filename
	 * @return
	 */
	public String save(InputStream inputStream, String contentType, String filename);

	/**
	 * Supprime la ressource dont le nom est passé en paramètre
	 * 
	 * @param filename
	 */
	public void deleteByFilename(String filename);

}
