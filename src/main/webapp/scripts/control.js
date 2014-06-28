$(document).ready(function(){
	textDefault();
    
    if($( "#chartTab").length > 0){
        $( "#chartTab").tabs();
    }


    $(".sitemap").click(function(e){
        e.preventDefault();
        var $this = $(this),
            $body = $('html, body');

        if(!$this.hasClass('active'))
        {
            $body.animate({ scrollTop: $(document).height() }, 1000);
        }
        $(".sitemap-block").slideToggle(function() {

        });
         $this.toggleClass('active');
    });

  
});
function textDefault(){
 $('input').focus(function(srcc)
    {
        if ($(this).val() == $(this)[0].title)
        {
            $(this).val("");
        }
    });
    $('input').blur(function()
    {
        if ($(this).val() == "")
        {
            $(this).val($(this)[0].title);
        }
    });
    $('input').blur();
}