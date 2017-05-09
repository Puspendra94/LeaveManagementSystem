$('document').ready(function(){
                $("#v").prop('selectedIndex', 0);
                $('#change').click(function(){
                    $('#p').dialog({
                        height: 300,
                        width: 400,
                         buttons: {
                        "Cancel": function() {
                    $( this ).dialog( "close" );
                    },
                    "Ok": function() {
                        //window.location.href = "Dialog2.jsp";
                        
                        var p = $('#v').val();
                        $(this).dialog("close");
                       // alert(p);
                        $('#t').val(p);
                    }
    }    
                    });
                    //$(".ui-dialog-titlebar").hide();
                });
                
                });