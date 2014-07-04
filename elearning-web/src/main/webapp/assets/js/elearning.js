$(document)
	.ready(
		function() {

		    // ***************
		    // FONCTIONS
		    // ***************
		    // Fonction de sérialisation des résultats d'un formulaire
		    // en pare de clé-valeurs
		    $.fn.serializeObject = function() {
			var result = {};
			var input = this.serializeArray();
			$.each(input, function() {
			    if (result[this.name] !== undefined) {
				if (!result[this.name].push) {
				    result[this.name] = [ result[this.name] ];
				}
				result[this.name].push(this.value || '');
			    } else {
				result[this.name] = this.value || '';
			    }
			});
			return result;
		    };
		    // Fonction de rendu dans le cadre AJAX
		    function display(target, event) {

			$("#ajaxMessage").empty();
			$("#ajaxPanel").empty();
			// Désactivation du lien
			event.preventDefault();
			$
				.ajax({
				    type : 'GET',
				    // URL déterminée par
				    // l'attribut href
				    url : $(target).attr('href'),
				    beforeSend : function() {
					// Enrichissement
					$('#ajaxMessage')
						.html(
							'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
				    },
				    success : function(data) {
					$('#ajaxMessage').empty();
					// Requete ok
					$('#ajaxPanel').empty();
					$("#ajaxPanel").append(data);
				    },
				    error : function() {
					$('#ajaxPanel').empty();
					// Requête ko
					$('#ajaxMessage')
						.html(
							'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez ressayer dans quelques instants, merci !</div>');
				    }
				});

		    }
		    ;
		    // Fonction de redirection
		    function redirectToUrl(redirectUrl, successMessage,
			    targetRender) {
			$
				.ajax({
				    type : 'GET',
				    // URL
				    // déterminée
				    // par
				    // l'attribut
				    // data-redirect_url
				    url : redirectUrl,
				    beforeSend : function() {
					// En
					// attente
					$('#ajaxMessage')
						.html(
							'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
				    },
				    success : function(data) {
					$('#ajaxMessage').empty();
					$(targetRender).empty();
					// Requete
					// ok
					if (!(typeof successMessage == "undefined" || successMessage == null)) {
					    $('#ajaxMessage').html(
						    successMessage);
					}
					$(targetRender).append(data);
				    },
				    error : function() {
					// Requête
					// ko
					$('#ajaxMessage')
						.html(
							'<div class="alert alert-danger alert-dismissable"><button class="close"aria-hidden="true" data-dismiss="alert"type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
				    }
				});
		    }
		    ;

		    // ***************
		    // EVENTS
		    // ***************
		    // Formulaire de recherche TODO
		    // Formulaire d'inscription TODO à migrer
		    $('#modalRegistrationForm').on(
			    'show.bs.modal',
			    function(e) {
				$("#modalRegistrationFormContent").empty();
				$.ajax({
				    url : "registration",
				    cache : true
				}).done(
					function(html) {
					    $("#modalRegistrationFormContent")
						    .append(html);
					});
			    });
		    // Affichage de listes dans le panneau central
		    $('a.displayLink').on('click', function(event) {
			// Appel de la fonction de rendu
			display($(this), event);
		    });
		    // // Liens display dans le cadre de rendu
		    $("#ajaxPanel").on('click', "a.displayLink",
			    function(event) {
				// Appel de la fonction de rendu
				display($(this), event);
			    });
		    // Suppression d'objets via ajax render
		    $("#ajaxPanel")
			    .on(
				    'click',
				    "a.deleteLink",
				    function(event) {
					$("#ajaxMessage").empty();
					// Désactivation du lien
					event.preventDefault();
					var redirectUrl = $(this).data(
						'redirect_url');
					var targetRender = $(this).data(
						'target_render');
					if ((typeof redirectUrl == "undefined" || redirectUrl == null)) {
					    redirectUrl = 'none';
					}

					$
						.ajax({
						    type : 'GET',
						    // URL déterminée par
						    // l'attribut href
						    url : $(this).attr('href'),
						    beforeSend : function() {
							// Enrichissement
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
						    },
						    success : function(data) {
							// Requete ok, la liste
							// est rechargée
							var successMessage = '<div class="alert alert-success alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> L\'entrée selectionnée a bien été supprimée.</div>';
							if (redirectUrl != 'none') {
							    redirectToUrl(
								    redirectUrl,
								    successMessage,
								    targetRender);
							} else {
							    $(targetRender)
								    .html(
									    data);
							    $('#ajaxMessage')
								    .html(
									    successMessage);
							}
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    $("#modalContent")
			    .on(
				    'click',
				    "a.redirectLink",
				    function(event) {
					$("#ajaxMessage").empty();
					// Désactivation du lien
					event.preventDefault();
					var redirectUrl = $(this).data(
						'redirect_url');
					var targetRender = $(this).data(
						'target_render');
					if ((typeof redirectUrl == "undefined" || redirectUrl == null)) {
					    redirectUrl = 'none';
					}

					$
						.ajax({
						    type : 'GET',
						    // URL déterminée par
						    // l'attribut href
						    url : $(this).attr('href'),
						    beforeSend : function() {
							// Enrichissement
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
						    },
						    success : function(data) {
							// Requete ok, la liste
							// est rechargée
							//var successMessage = '<div class="alert alert-success alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> Les ressources ont bien été ajoutées.</div>';
							if (redirectUrl != 'none') {
							    redirectToUrl(
								    redirectUrl,
								    "",
								    targetRender);
							} else {
							    $(targetRender)
								    .append(
									    data);
							    $('#ajaxMessage')
								    .html(
									    successMessage);
							}
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    $(".ajax-enabled")
		    .on(
			    'click',
			    "a.redirectLink",
			    function(event) {
				$("#ajaxMessage").empty();
				// Désactivation du lien
				event.preventDefault();
				var redirectUrl = $(this).data(
					'redirect_url');
				var targetRender = $(this).data(
					'target_render');
				if ((typeof redirectUrl == "undefined" || redirectUrl == null)) {
				    redirectUrl = 'none';
				}

				$
					.ajax({
					    type : 'GET',
					    // URL déterminée par
					    // l'attribut href
					    url : $(this).attr('href'),
					    beforeSend : function() {
						// Enrichissement
						$('#ajaxMessage')
							.html(
								'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
					    },
					    success : function(data) {
						// Requete ok, la liste
						// est rechargée
						//var successMessage = '<div class="alert alert-success alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> Les ressources ont bien été ajoutées.</div>';
						if (redirectUrl != 'none') {
						    redirectToUrl(
							    redirectUrl,
							    "",
							    targetRender);
						} else {
						    $(targetRender)
							    .append(
								    data);
						    $('#ajaxMessage')
							    .html(
								    successMessage);
						}
					    },
					    error : function() {
						// Requête ko
						$('#ajaxMessage')
							.html(
								'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
					    }
					});
			    });
		   
		    // Détails d'une entité, rendu dans un modal
		    $("#ajaxPanel")
			    .on(
				    'click',
				    'a.detailsLink',
				    function(event) {
					event.preventDefault();
					$("#modalContent").empty();
					$("#ajaxMessage").empty();
					$
						.ajax({
						    type : 'GET',
						    // URL déterminée par
						    // l'attribut href
						    url : $(this).attr('href'),
						    beforeSend : function() {
							// En attente
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
						    },
						    success : function(data) {
							$('#ajaxMessage')
								.empty();
							// Requete ok
							$("#modalContent")
								.append(data);
							$("#modal").modal({
							    show : true
							});
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close"aria-hidden="true" data-dismiss="alert"type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    // Détails d'une entité, rendu dans un modal
		    $("a.detailsLink")
			    .on(
				    'click',
				    function(event) {
					event.preventDefault();
					$("#modalContent").empty();
					$("#ajaxMessage").empty();
					$
						.ajax({
						    type : 'GET',
						    // URL déterminée par
						    // l'attribut href
						    url : $(this).attr('href'),
						    beforeSend : function() {
							// En attente
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
						    },
						    success : function(data) {
							$('#ajaxMessage')
								.empty();
							// Requete ok
							$("#modalContent")
								.append(data);
							$("#modal").modal({
							    show : true
							});
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close"aria-hidden="true" data-dismiss="alert"type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    // Détails d'une entité, rendu pas dans un modal
		    $("#ajaxPanel").on('click', 'a.displayLinkNotModal',
			    function(event) {
				display($(this), event);
			    });
		    // Formulaire d'ajout dans panneau AJAX
		    $("#ajaxPanel")
			    .on(
				    'click',
				    'a.addForm',
				    function(event) {
					event.preventDefault;
					$("#modalContent").empty();
					$
						.ajax({
						    type : 'GET',
						    // URL déterminée par
						    // l'attribut href
						    url : $(this).attr('href'),
						    // Formulaire de saisie,
						    // toujours le
						    // même = en cache
						    // !!
						    cache : true,
						    beforeSend : function() {
							// En attente
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
						    },
						    success : function(data) {
							$('#ajaxMessage')
								.empty();
							// Requete ok
							$("#modalContent")
								.append(data);
							$("#modal").modal({
							    show : true
							});

							// $j.ajax({
							// type: "GET",
							// url: "http://" +
							// disqus_shortname +
							// ".disqus.com/embed.js",
							// dataType: "script",
							// cache: true
							// });
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    // Soumission de formulaire d'ajout
		    // Le formulaire est dispatché dans un modal et non dans
		    // #ajaxPanel !!!!
		    $("#modalContent")
			    .on(
				    "submit",
				    "form.addForm",
				    function(event) {
					// Pas de soumission
					event.preventDefault();
					var redirectUrl = $(this).data(
						'redirect_url');
					var targetRender = $(this).data(
						'target_render');

					if ((typeof redirectUrl == "undefined" || redirectUrl == null)) {
					    redirectUrl = 'none';
					}
					var formData = JSON.stringify($(this)
						.serializeObject());
					$
						.ajax({
						    type : "POST",
						    // L'url cible est celle du
						    // formulaire
						    url : $(this)
							    .attr('action'),
						    // On retourne le
						    // formulaire tel
						    // quel, en le serializant
						    data : formData,
						    // On envoie du json, le
						    // content type dans le
						    // header doit le dire
						    contentType : 'application/json',
						    mimeType : 'application/json',
						    // Type que nous attendons
						    // en retour
						    dataType : "html",
						    success : function(data) {
							// Reset du modal et
							// disparition
							$("#modalContent")
								.text("");
							$('#modal').modal(
								'hide');
							// si une URL de
							// redirection est
							// spécifiée, nouveau
							// get en AJAX

							$('#ajaxPanel').empty();
							if (redirectUrl != 'none') {
							    redirectToUrl(
								    redirectUrl,
								    '',
								    targetRender);
							} else {
							    $(targetRender)
								    .append(
									    data);
							    // $('#ajaxMessage')
							    // .html('');
							}
						    },
						    error : function() {
							// Requête ko
							// Reset du modal et
							// disparition
							$("#modalContent")
								.text("");
							$('#modal').modal(
								'hide');
							// Mise à jour du panel
							$('#ajaxPanel').empty();
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage... Veuillez ressayer dans quelques instants, merci !</div>');
						    }

						});

				    });
		    // Formulaire d'édition dans panneau AJAX
		    $("#ajaxPanel")
			    .on(
				    'click',
				    "a.editLink",
				    function(event) {
					event.preventDefault();
					$("#modalContent").empty();
					$("#ajaxMessage").empty();
					$
						.ajax({
						    type : 'GET',
						    // URL déterminée
						    // par
						    // l'attribut href
						    url : $(this).attr('href'),
						    beforeSend : function() {
							// En attente
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-info alert-dismissable"><p>Loading...</p></div>');
						    },
						    success : function(data) {
							$('#ajaxMessage')
								.empty();
							// Requete ok
							$("#modalContent")
								.append(data);
							$("#modal").modal({
							    show : true
							});
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close"aria-hidden="true" data-dismiss="alert"type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });

		    // Soumission de formulaire d'ajout
		    // Le formulaire est dispatché dans un modal et non dans
		    // #ajaxPanel !!!!
		    $("#modalContent")
			    .on(
				    "submit",
				    "form.editForm",
				    function(event) {
					// Pas de soumission
					event.preventDefault();
					var redirectUrl = $(this).data(
						'redirect_url');
					var targetRender = $(this).data(
						'target_render');

					if ((typeof redirectUrl == "undefined" || redirectUrl == null)) {
					    redirectUrl = 'none';
					}
					var formData = JSON.stringify($(this)
						.serializeObject());
					$
						.ajax({
						    type : "POST",
						    // L'url cible est celle du
						    // formulaire
						    url : $(this)
							    .attr('action'),
						    // On retourne le
						    // formulaire tel
						    // quel, en le serializant
						    data : formData,
						    // On envoie du json, le
						    // content type dans le
						    // header doit le dire
						    contentType : 'application/json',
						    mimeType : 'application/json',
						    // Type que nous attendons
						    // en retour
						    dataType : "html",
						    success : function(data) {
							// Reset du modal et
							// disparition
							$("#modalContent")
								.text("");
							$('#modal').modal(
								'hide');
							// si une URL de
							// redirection est
							// spécifiée, nouveau
							// get en AJAX

							$('#ajaxPanel').empty();
							if (redirectUrl != 'none') {
							    redirectToUrl(
								    redirectUrl,
								    '',
								    targetRender);
							} else {
							    $(targetRender)
								    .append(
									    data);
							    // $('#ajaxMessage')
							    // .html('');
							}
						    },
						    error : function() {
							// Requête ko
							// Reset du modal et
							// disparition
							$("#modalContent")
								.text("");
							$('#modal').modal(
								'hide');
							// Mise à jour du panel
							$('#ajaxPanel').empty();
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage... Veuillez ressayer dans quelques instants, merci !</div>');
						    }

						});

				    });
		});