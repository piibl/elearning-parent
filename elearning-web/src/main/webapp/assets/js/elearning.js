$(document)
	.ready(
		function() {

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
		    $('.displayLink')
			    .on(
				    'click',
				    function(event) {
					$("#ajaxMessage").empty();
					$("#ajaxPanel").empty();
					// Désactivation du lien
					event.preventDefault();
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
							$('#ajaxMessage')
								.empty();
							// Requete ok
							$('#ajaxPanel').empty();
							$("#ajaxPanel").append(
								data);
						    },
						    error : function() {
							$('#ajaxPanel').empty();
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    // Suppression d'objets
		    $("#ajaxPanel")
			    .on(
				    'click',
				    "a.deleteLink",
				    function(event) {
					$("#ajaxMessage").empty();
					// Désactivation du lien
					event.preventDefault();
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
							$('#ajaxPanel').empty();
							$("#ajaxPanel").append(
								data);
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-success alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> L\'entrée selectionnée a bien été supprimée.</div>');
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });
		    // Détails d'une entité
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
		    // Formulaire d'ajout dans panneau AJAX
		    $("#ajaxPanel")
			    .on(
				    'click',
				    "a.addForm",
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
							// Requete ok
							// Reset du modal et
							// disparition
							$("#modalContent")
								.empty();
							$('#modal').modal(
								'hide');
							// Affichage du message
							$('#ajaxMessage').empty().append('<div class="alert alert-success alert-dismissable"><button class="close" aria-hidden="true" data-dismiss="alert"type="button">×</button>L\'instance a bien été crée.</div>');
							// Mise à jour du panel
							$('#ajaxPanel').empty();
							$("#ajaxPanel").append(
								data);
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
							// Requete ok
							// Reset du modal et
							// disparition
							$("#modalContent")
								.empty();
							$('#modal').modal(
								'hide');

							// Mise à jour du panel
							$('#ajaxPanel').empty();
							$("#ajaxPanel").append(
								data);
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
		 // Détails d'une entité
		    $("#ajaxPanel")
			    .on(
				    'click',
				    'a.sessionsLink',
				    function(event) {
					alert("click");
					event.preventDefault();
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
							$('#ajaxPanel')
							.empty();
							// Requete ok
							$('#ajaxPanel')
								.append(data);
						    },
						    error : function() {
							// Requête ko
							$('#ajaxMessage')
								.html(
									'<div class="alert alert-danger alert-dismissable"><button class="close"aria-hidden="true" data-dismiss="alert"type="button">×</button> <strong>Ooops !</strong> Petit plantage, veuillez  ressayer dans quelques instants, merci !</div>');
						    }
						});
				    });

		});