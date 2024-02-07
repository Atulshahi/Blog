package practice.atul.springboot.payload;

import lombok.Data;
import lombok.Getter;

@Data
public class PostDto {
	
	private Long id;
	private String tittle;
	private String description;
	private String content;
	
	
//	public String getTittle() {
//		return tittle;
//	}
//	
//	public String getDescription() {
//		return description;
//	}
//		public String getContent() {
//		return content;
//	}
	
	
	
	
}
