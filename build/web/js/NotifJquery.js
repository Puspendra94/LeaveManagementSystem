$('document').ready(function(){
               
                $('.cb').click(function(){
                    if($('.cb:checked').length===1)
                    {
                        $('#panel').show("slow");
                    }
                    else
                    {
                        $('#panel').hide("slow");
                    }
                });
      
            });