$(document).ready(function() {
	
	
	  var savedPageNo = sessionStorage.getItem('currentPage') || 1;
	  var savedSearchType = sessionStorage.getItem('searchType') || '';
	  var savedSearchText = sessionStorage.getItem('searchText') || ''; 
             
	listBoards(savedPageNo,savedSearchType,savedSearchText);
    $("#search-btn").click(function() {    	
    	var searchType = $('#searchType').val();
		var searchText = $('#searchText').val();
		sessionStorage.setItem('searchType', searchType);
		sessionStorage.setItem('searchText', searchText);
		listBoards(1,searchType,searchText);
    });
    function listBoards(pageNo,searchType,searchText) {  
    	var dataFormat={// 기본 셋팃값
    			pageNo: pageNo
    	}
    	var dataUrl = "boardList/Select.do"		   
		   if(searchType && searchText)
		   {
			   $('#searchType').val(searchType);
			   $('#searchText').val(searchText);
			  dataFormat = {
					   pageNo: pageNo,
	            	   searchType: searchType,
	            	   searchText: searchText
			  } 
			  dataUrl = "boardList/SearchSelect.do";
		   }
    	   sessionStorage.setItem('currentPage', pageNo); 
		   $.ajax({
	            url: dataUrl,
	            method: "GET",
	            data: dataFormat,
	            dataType :"json",
	            success: function(response) {
	                if (response && response.boardList) {
	                	
	                    var boardList = response.boardList;
	                    console.log(boardList);
	                    $("#boards-list").empty();
	                    boardList.forEach(function(board) {	                    	
	                        $("#boards-list").append(
	                        		`<tr class="table-active">
	            					<td id="boardId"><a href="boardView.do?boardId=${board.boardId}&viewSet=yes">${board.boardId}</a></td>
	            					<td>${ board.boardTitle}</td>
	            					<td>${ board.boardContent}</td>
	            					<td>${ board.boardDate}</td>
	            					<td>${ board.userName}</td>
	            					<td>${ board.viewsCount}</td>
	            				</tr>`
	                        );
	                    });
	                console.log('페이징 처리를 위한 페이지 객체',response.pageManager);
	                var pageManager = response.pageManager;
                    var currentPage = pageManager.pageNo;
                    var totalPages = pageManager.totalPagecount;
                    $("#pageList").empty();
                    for(var page=1;page<=totalPages;page++)
                    {
                    	 if (page == currentPage) {
                    	        $("#pageList").append(`<span style="margin-right: 5px; color: red;">${page}</span>`);
                    	    } else {
                    	        $("#pageList").append(`<span style="margin-right: 5px">${page}</span>`);
                    	    }
                    }
                    
                    if (currentPage <= 1) {
                        $("#prev-btn").prop("disabled", true);
                    } else {
                        $("#prev-btn").prop("disabled", false);
                        $("#prev-btn").off('click').on('click', function() {
                            listBoards(currentPage - 1,searchType,searchText);
                        });
                    }

                   
                    if (currentPage >= totalPages) {
                        $("#next-btn").prop("disabled", true);
                    } else {
                        $("#next-btn").prop("disabled", false);
                        $("#next-btn").off('click').on('click', function() {
                            listBoards(currentPage + 1,searchType,searchText);
                        });
                    }
                }
            },
            error: function(err) {
                console.error("게시판 불러오기 중 오류 발생:", err);
            }
        });
    }
	       
});