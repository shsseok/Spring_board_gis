
//원래의 게시판에서는 게시판 검색과 게시판 조회의 기능에 대한 페이징 처리 버그 이유로 따로 자바스크립트를 분리해서 진행하였다.

$(document).ready(function() { 
$("#search-btn").click(function() {
	   function searchListBoards(pageNo) {
		   console.log("dd");
		   	var searchType = $('#searchType').val();
	        var searchText = $('#searchText').val();
	        $.ajax({
	            url: "boardList/SearchSelect.do",
	            method: "GET",
	            data: {pageNo: pageNo,
	            	   searchType: searchType,
	            	   searchText: searchText},
	            dataType :"json",
	            success: function(response) {
	                if (response && response.boardList) {
	                    var boardList = response.boardList;
	                    console.log(boardList);
	                    $("#boards-list").empty();
	                    boardList.forEach(function(board) {	                    	
	                        $("#boards-list").append(
	                        	   `<tr class="table-active">
	            					<td id="boardId"><a href="boardView.do?boardId=${board.boardId}">${board.boardId}</a></td>
	            					<td>${ board.boardTitle}</td>
	            					<td>${ board.boardContent}</td>
	            					<td>${ board.boardDate}</td>
	            					<td>${ board.userName}</td>
	            				</tr>`
	                        );
	                    });
	             console.log(response.pageManager);
	             var pageManager = response.pageManager;
                 var currentPage = pageManager.pageNo;
                 var totalPages = pageManager.totalPagecount;
                 console.log("전체 페이지 개수",totalPages);
                 $("#pageList").empty();
                 for(var page=1;page<=totalPages;page++)
                 {
                 	 if (page == currentPage) 
                 	 {
                 	        $("#pageList").append(`<span style="margin-right: 5px; color: red;">${page}</span>`);
                 	 } else 
                 	 {
                 	        $("#pageList").append(`<span style="margin-right: 5px">${page}</span>`);
                 	 }
                 }
                 
                 if (currentPage <= 1) {
                     $("#prev-btn").prop("disabled", true);
                 } else {
                     $("#prev-btn").prop("disabled", false);
                     $("#prev-btn").off('click').on('click', function() {
                    	 searchListBoards(currentPage - 1);
                     });
                 }

                
                 if (currentPage >= totalPages) {
                     $("#next-btn").prop("disabled", true);
                 } else {
                     $("#next-btn").prop("disabled", false);
                     $("#next-btn").off('click').on('click', function() {
                    	 searchListBoards(currentPage + 1);
                     });
                 }
             }
         },
         error: function(err) {
             console.error("게시판 검색 중 오류 발생:", err);
         }
     });
 }
	   searchListBoards(1);		    
});
});