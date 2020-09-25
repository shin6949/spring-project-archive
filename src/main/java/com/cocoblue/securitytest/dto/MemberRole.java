package com.cocoblue.securitytest.dto;

public class MemberRole {
    private long id;
    private long memberId;
    private String roleName;

    public MemberRole() {
    }

    public MemberRole(long memberId, String roleName) {
        this.memberId = memberId;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "MemberRole{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
