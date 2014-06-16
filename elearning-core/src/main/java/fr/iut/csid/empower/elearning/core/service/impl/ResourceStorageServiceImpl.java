package fr.iut.csid.empower.elearning.core.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.inject.Named;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import fr.iut.csid.empower.elearning.core.service.ResourceStorageService;

@Named
public class ResourceStorageServiceImpl implements ResourceStorageService {

	@Autowired
	private GridFsOperations gridOperation;

	@Override
	public String save(InputStream inputStream, String contentType, String filename) {

		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", filename);
		metaData.put("meta2", contentType);

		GridFSFile file = gridOperation.store(inputStream, filename, metaData);

		return file.getId().toString();
	}

	@Override
	public GridFSDBFile get(String id) {
		return gridOperation.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))));
	}

	@Override
	public List<GridFSDBFile> listResources() {

		return gridOperation.find(null);
	}

	@Override
	public void deleteByFilename(String filename) {

	}

	@Override
	public GridFSDBFile getByResourceName(String filename) {
		return gridOperation.findOne(new Query(Criteria.where("filename").is(filename)));
	}
}
