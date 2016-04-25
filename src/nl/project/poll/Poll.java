package nl.project.poll;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Poll {

	@Column(name="id")
	private Long id;
	private String title;
	private List<String> options;
	
	Map<String, Integer> choices;
	private String result;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ElementCollection
	@CollectionTable(name="Options", joinColumns=@JoinColumn(name="poll_id"))
	@Column(name="option")
	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public Map<String, Integer> getChoices() {
		return choices;
	}

	public void setChoices(Map<String, Integer> choices) {
		this.choices = choices;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
