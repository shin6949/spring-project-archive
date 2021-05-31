<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">책 정보</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">${book.name}</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

          <div class="form-group">
          <label>내부 분류 ID</label> <input class="form-control" name='id'
            value='<c:out value="${book.id }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>글쓴이</label> <input class="form-control" name='writer'
                                      value='<c:out value="${book.writer }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>ISBN</label> <input class="form-control" name='isbn'
                                      value='<c:out value="${book.isbn }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>카테고리 명(코드)</label> <input class="form-control" name='categoryName'
                                      value='<c:out value="${book.categoryName}(${book.categoryCode})"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>책 위치</label> <input class="form-control" name='location'
                                      value='<c:out value="${book.location }"/>' readonly="readonly">
        </div>

        <div class="form-group">
          <label>상태</label>
          <c:if test="${book.isBorrowed eq true}">
            <input class="form-control" name='isBorrowed'
                   value='대출 중' readonly="readonly">
          </c:if>

          <c:if test="${book.isBorrowed ne true}">
            <input class="form-control" name='isBorrowed'
                   value='대출 가능' readonly="readonly">
          </c:if>

        </div>

<c:if test="${book.isBorrowed ne true}">
  <button data-oper='return' class="btn btn-default">반납처리</button>
</c:if>

<button data-oper='list' class="btn btn-info">리스트</button>

<form id='operForm' action="/book/return" method="get">
  <input type='hidden' id='id' name='id' value='<c:out value="${book.id}"/>'>
</form>

<%--
<form id='operForm' action="/boad/modify" method="get">
  <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
  <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
  <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
  <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
  <input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>  
</form>
 --%>


      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
$(document).ready(function() {
  
  var operForm = $("#operForm"); 
  
  $("button[data-oper='modify']").on("click", function(e){
    
    operForm.attr("action","/board/modify").submit();
    
  });
  
    
  $("button[data-oper='list']").on("click", function(e){
    
    operForm.find("#bno").remove();
    operForm.attr("action","/board/list")
    operForm.submit();
    
  });  
});
</script>


<%@include file="../includes/footer.jsp"%>
