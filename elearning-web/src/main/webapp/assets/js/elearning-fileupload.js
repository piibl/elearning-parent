$(function() {
    $('#fileupload')
	    .fileupload(
		    {
			dataType : 'json',
			done : function(e, data) {
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
												file.originalFilename))
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
		    });
});