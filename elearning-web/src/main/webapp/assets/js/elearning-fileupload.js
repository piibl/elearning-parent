$(function () {
    $('#fileupload')
	    .fileupload(
		    {
			dataType : 'application/json',
			done : function(e, data) {
			    $("tr:has(td)").remove();
			    $
				    .each(
					    data.result,
					    function(index, file) {

						$("#uploaded-files")
							.append(
								$('<tr/>')
									.append(
										$(
											'<td/>')
											.text(
												file.name))
									.append(
										$(
											'<td/>')
											.text(
												file.fileSize))
									.append(
										$(
											'<td/>')
											.text(
												file.fileType)));// end
					    });
			},

			progressall : function(e, data) {
			    var progress = parseInt(data.loaded / data.total
				    * 100, 10);
			    $('#progress .bar').css('width', progress + '%');
			},
		    });
});