package com.cos.iter.web.dto;

import java.util.List;

import com.cos.iter.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRespDto {
	private boolean pageHost; // 페이지의 주인 확인
	private boolean followState; // true(doFollow 취소), false(doFollow)
	private User user;
	private List<UserProfilePostRespDto> posts;
	private int followerCount;
	private int followingCount;
	private int postCount;
}
