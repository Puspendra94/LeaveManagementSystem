
var test = function(){
    $.ajax({
                url: '/LeaveManagement/AutoNotifCountServlet',
                
                dataType: 'json',
               
                success: function (data) {
                    /*response(data);*/
                    //$('#noti').text(data);
                            $('#notification_count').text(data);
                   // alert(data);
                },
                
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error in the Database connection!");
                }
                
    });
        
};


// call the function when the DOM is ready
$("document").ready(test);