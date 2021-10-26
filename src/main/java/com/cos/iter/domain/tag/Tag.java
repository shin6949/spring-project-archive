package com.cos.iter.domain.tag;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.cos.iter.domain.post.Post;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@ManyToOne
	@JoinColumn(name="post_id", foreignKey = @ForeignKey(name="FK_TAG_POST_ID"))
	private Post post;
	
	@CreationTimestamp
	private LocalDateTime createDate;
}
