
 $('document').ready(function(){
     $(':submit').click(function(event){
         var a = $(this).prop("value");
         
         if(a === "Delete")
         {
             var ans = window.confirm('Do you really want to delete ?' );
        
             return ans;
         }
     });
 });