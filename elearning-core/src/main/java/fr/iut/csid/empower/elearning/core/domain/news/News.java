package fr.iut.csid.empower.elearning.core.domain.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS")
public class News {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NewsSeq")
	@SequenceGenerator(name = "NewsSeq", sequenceName = "NEWS_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "NEWS_ID", nullable = false)
	@Id
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	public News() {

	}

	/**
	 * @param title
	 * @param content
	 */
	public News(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
