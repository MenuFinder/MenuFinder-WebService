$(function(){
    $(".collapsible-header").click(function(event) {
    event.preventDefault();
    $(this).next().slideToggle();
  });
  $(".collapsible a").click(function(event) {
    event.preventDefault();
    $(".collapsible").slideUp();
  });
  });

$(document).ready(function()
{

    var updateOutput = function(e)
    {
        var list   = e.length ? e : $(e.target),
            output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
        } else {
            output.val('JSON browser support required for this demo.');
        }
    };

    // activate Nestable for list 1
    $('#nestable').nestable({
        group: 1
    })
    .on('change', updateOutput);
    
    // output initial serialised data
    updateOutput($('#nestable').data('output', $('#nestable-output')));


});