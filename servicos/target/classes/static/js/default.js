$('#confirmacaoRemoverClienteModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var codigoCliente = button.data('codigo');
	var nomeCliente = button.data('nome')
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if (!action.endsWith('/')) {
		action += '/';
	}
	
	form.attr('action', action + codigoCliente);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o cliente <strong> ' + nomeCliente + '</strong>?');
	
});

$('#confirmacaoRemoverServicoModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var codigoServico = button.data('codigo');
	var nomeServico = button.data('nome')
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if (!action.endsWith('/')) {
		action += '/';
	}
	
	form.attr('action', action + codigoServico);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o serviço <strong> ' + nomeServico + '</strong>?');
	
});

$('#confirmacaoRemoverServicoClienteModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var codigoServico = button.data('codigo');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if (!action.endsWith('/')) {
		action += '/';
	}
	
	form.attr('action', action + codigoServico);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o serviço do cliente');
	
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
})