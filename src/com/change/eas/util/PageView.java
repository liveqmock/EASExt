package com.change.eas.util;

public class PageView {
      public PageView ( int pageSize, int allRows, int currPage, String url ) {
            this.pageSize = pageSize;
            this.allRows = allRows;
            this.currPage = currPage;
            this.url = url;
      }

      public static void main ( String [] args ) {
            PageView view = new PageView(10, 45, 2, "http://www.baidu.com");
            System.out.println(view.pageView());
      }

      public String pageView () {
    	  String str = "<select name='pageSize' onchange='javascript:window.location.href=\""+this.getUrl()+"newPageSize=\"+this.value'><option";
    	  if(pageSize==5){
    		  str+=" selected='selected'";
    	  }
    	  str+=" value='5'>5</option><option";
    	  if(pageSize==10){
    	  str+=" selected='selected'";
    	  }
    	  str+=" value='10'>10</option><option";
    	  if(pageSize==15){
    	  str+=" selected='selected'";
    	  }
    	  str+=" value='15'>15</option><option";
    	  if(pageSize==20){
    	  str+=" selected='selected'";  
    	  }
    	  str+=" value='20'>20</option></select>";
//    	  str+=" value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select>";
            StringBuffer sb = new StringBuffer(str+"共有" + allRows + "条记录");
            sb.append(" (" + this.getBeginRow() + "-" + (this.getEndRow()-1) + ")");
            sb.append(" " + currPage + "/" + this.getPageCount());
            if ( currPage != 1 ) {
                  sb.append(" <a target=\"_self\" href=\"" + this.getUrl() + "current=1\">首页</a>");
            }
            if ( currPage < this.getPageCount() ) {
                  sb.append(" <a target=\"_self\" href=\"" + this.getUrl() + "current=" + ( currPage + 1 ) + "\">下页</a>");
            }
            if ( currPage > 1 ) {
                  sb.append(" <a target=\"_self\" href=\"" + this.getUrl() + "current=" + ( currPage - 1 ) + "\">上页</a>");
            }
            if ( currPage != this.getPageCount() ) {
                  sb.append(" <a target=\"_self\" href=\"" + this.getUrl() + "current=" + this.getPageCount() + "\">末页</a>");
            }
            if ( this.getPageCount() != 1 ) {
                  sb.append(" 跳转到第");
                  sb.append("<input style=\"width:20px\" id='input' name='page' type='text' maxlength='4' onchange=\"if(this.value<=" + this.getPageCount() + "&&this.value>0) window.open('" + this.getUrl()
                              + "current='+this.value,'_self');else alert('请正确输入页码！');\"/>页");
            }
            return sb.toString();
      }

      private int pageSize;//一页条数
      private int allRows;//总条数
      private int currPage;//当前页数
      private String url;//页面链接

      /**
       * @author guzi
       * @function 
       * @process 
       * @return 总页数
       */
      public int getPageCount () {
            return (int) Math.ceil(1.0 * allRows / pageSize);
      }

      public int getEndRow () {
            int i = this.getBeginRow() + pageSize;
            if ( i > allRows ) {
                  i = allRows + 1;
            }
            return i;
      }

      public int getBeginRow () {
            return ( currPage - 1 ) * pageSize + 1;
      }

      public int getPageSize () {
            return pageSize;
      }

      public void setPageSize ( int pageSize ) {
            this.pageSize = pageSize;
      }

      public int getAllRows () {
            return allRows;
      }

      public void setAllRows ( int allRows ) {
            this.allRows = allRows;
      }

      public int getCurrPage () {
            return currPage;
      }

      public void setCurrPage ( int currPage ) {
            this.currPage = currPage;
      }

      public String getUrl () {
            if ( url.contains("?") ) {
                  return url + "&";
            }
            else {
                  return url + "?";
            }
      }

      public void setUrl ( String url ) {
            this.url = url;
      }

}
