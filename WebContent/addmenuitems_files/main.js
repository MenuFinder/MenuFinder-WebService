var ajustesModulesIpad = (function () {
    var texto = {
        selectorInformesIpad: '.informes-ipad',
        typeB: 'typeB'
    };
    function init(contenedor) {
        contenedor.addClass(texto.typeB);
        contenedor.find(texto.selectorInformesIpad).show();
    }
    return {
        init: init
    };
}());

var ordenaYEstableceElAltoMayor = (function () {
    function ordenaArrayDeMayorAMenor(element1, element2) {
        return (element2 - element1);
    }
    function init(elementos, altosElementos) {
        altosElementos.sort(ordenaArrayDeMayorAMenor);
        elementos.height(altosElementos[0]);
    }
    return {
        init: init
    };
}());

var ajustaAltos = (function () {
    function init(contenedor) {
        var $columnas = contenedor.children(),
            altosColumnas = [],
            altoColumna;

        $($columnas).each(function () {
            altoColumna = $(this).height();
            altosColumnas.push(altoColumna);
        });

        ordenaYEstableceElAltoMayor.init($columnas, altosColumnas);
    }
    return {
        init: init
    };
}());

var faqDesplegables = (function () {
    var texto = {
        selectorDesplegar: '.desplegar',
        selectorTexto: '.texto'
    };
    function init(contenedor) {
        var $enlaces = contenedor.find(texto.selectorDesplegar);

        $enlaces.on('click', function (ev) {
            ev.preventDefault();
            $(this).next(texto.selectorTexto).toggle();
        });
    }
    return {
        init: init
    };
}());

$.fn.carrusel = function (options) {
    var defaults = {
        opcionesAnimacion : {
            tiempoTransicion: 6000,
            medida: 'px'
        },
        selectorSlides: '.slides',
        selectorAnterior: '.anterior',
        selectorSiguiente: '.siguiente'
    },
        texto = $.extend(defaults, options);

    function carrusel(el) {
        var contadorCapa = 0,
            $contenedor = el.find(texto.selectorSlides),
            $slides =  $contenedor.children(),
            numSlides = $slides.length,
            $anterior = el.find(texto.selectorAnterior),
            $siguiente = el.find(texto.selectorSiguiente),
            medidaUnElemento = el.width();

       
        function oculta(elemento) {
            elemento.hide();
        }
        function muestra(elemento) {
            elemento.show();
        }
        function revisaVisibilidadControles() {
            var esPrimerSlide = (contadorCapa === 0),
                esUltimoSlide = (contadorCapa === (numSlides - 1));

            if (esPrimerSlide) {
                oculta($anterior);
                if (numSlides > 1) {
                    muestra($siguiente);
                }
            } else if (esUltimoSlide) {
                muestra($anterior);
                oculta($siguiente);
            } else {
                muestra($anterior);
                muestra($siguiente);
            }
        }
        function mueve(operacion) {
            var esSuma = (operacion === 'suma');

            if (esSuma) {
                $contenedor.animate({
                    left: '+=' + medidaUnElemento + texto.opcionesAnimacion.medida
                }, 'fast');
            } else {
                $contenedor.animate({
                    left: '-=' + medidaUnElemento + texto.opcionesAnimacion.medida
                }, 'fast');
            }
        }
        function controles() {
            var esClick,
                esTab,
                esUltimoSlide;

            $anterior.off().on('click keypress', function (ev) {
                esClick = (ev.type === 'click');
                esTab = (ev.keyCode === 9);

                if (esClick || !esTab) {
                    ev.preventDefault();
                    ev.stopPropagation();

                    mueve('suma');
                    contadorCapa = (contadorCapa - 1);
                    revisaVisibilidadControles();

                    // pintaTabIndexInvisible();
                    // pintaTabindexVisible(contadorCapa);
                }
            }).stop();

            $siguiente.off().on('click keypress', function (ev) {
                esClick = (ev.type === 'click');
                esTab = (ev.keyCode === 9);
                esUltimoSlide = (contadorCapa === (numSlides - 1));

                if (esClick || !esTab) {
                    ev.preventDefault();
                    ev.stopPropagation();

                    if (!esUltimoSlide) {
                        mueve('resta');
                        contadorCapa = (contadorCapa + 1);
                        revisaVisibilidadControles();

                        // pintaTabIndexInvisible();
                        // pintaTabindexVisible(contadorCapa);
                    }
                }
            }).stop();
        }
        function inicia() {
            // pintaTabIndexInvisible();
            // pintaTabindexVisible(contadorCapa);

            revisaVisibilidadControles();
            controles();
        }

        inicia();
    }

    return this.each(function () {
        var el = $(this);
        carrusel(el);
    });
};

$.fn.tabs = function () {
    var texto = {
        selectorMenuTabs: '.menu-tabs li',
        selectorPanel: '.panel',
        panel: 'panel',
        tab: 'tab',
        activo: 'activo'
    };

    function tabs(el) {
        var $pestanyas = el.find(texto.selectorMenuTabs),
            $contenidos = el.find(texto.selectorPanel),
            $pestanyaInicial = $pestanyas.eq(0),
            $contenidoInicial = $contenidos.eq(0);

        function pintaClasesIniciales() {
            $pestanyas.each(function (i) {
                $(this).attr('id', texto.tab + i);
            });
            $contenidos.each(function (i) {
                $(this).attr('id', texto.panel + i);
            });
        }
        function pintaClaseActivo(elemento) {
            elemento.addClass(texto.activo);
        }
        function quitaClaseActivo(elemento) {
            elemento.removeClass(texto.activo);
        }
        function pintaAriaSelectedPestanyas() {
            var self,
                tieneClaseActivo;

            $pestanyas.each(function () {
                self = $(this);
                tieneClaseActivo = self.hasClass(texto.activo);

                if (tieneClaseActivo) {
                    self.attr('aria-selected', true);
                } else {
                    self.attr('aria-selected', false);
                }
            });
        }
        function pintaAriaHiddenContenido() {
            var self,
                tieneClaseActivo;

            $contenidos.each(function () {
                self = $(this);
                tieneClaseActivo = self.hasClass(texto.activo);

                if (tieneClaseActivo) {
                    self.attr('aria-hidden', false);
                } else {
                    self.attr('aria-hidden', true);
                }
            });
        }
        function funcionamientoPestanyas(pestanya) {
            var numPestanya = pestanya.attr('id').replace(texto.tab, ''),
                $contenidoSeleccionado = $contenidos.eq(numPestanya);

            quitaClaseActivo($pestanyas);
            pintaClaseActivo(pestanya);

            quitaClaseActivo($contenidos);
            pintaClaseActivo($contenidoSeleccionado);

            pintaAriaSelectedPestanyas();
            pintaAriaHiddenContenido();
        }
        function inicia() {
            var pestanya;

            pintaClasesIniciales();
            pintaClaseActivo($pestanyaInicial);
            pintaClaseActivo($contenidoInicial);

            pintaAriaSelectedPestanyas();
            pintaAriaHiddenContenido();

            $pestanyas.on('click', function (ev) {
                pestanya = $(this);

                ev.preventDefault();
                funcionamientoPestanyas(pestanya);
            });
        }

        inicia();
    }

    return this.each(function () {
        var el = $(this);
        tabs(el);
    });
};

var grafica = (function () {
    var texto = {
    };
    function init(contenedor) {
        var $areaDeLasGraficas = $('.carrusel'),
            ESPACIO_VERTICAL = 20,
            widthAreaDeLasGraficas = $areaDeLasGraficas.width(),
            heightAreaDeLasGraficas = $areaDeLasGraficas.height() - ESPACIO_VERTICAL,
            margin = {top: 50, right: 10, bottom: 30, left: 50},
            width = widthAreaDeLasGraficas - margin.left - margin.right,
            height = heightAreaDeLasGraficas - margin.top - margin.bottom;

        var x0 = d3.scale.ordinal()
            .rangeRoundBands([0, width], .1);

        var x1 = d3.scale.ordinal();

        var y = d3.scale.linear()
            .range([height, 0]);

        var color = d3.scale.ordinal()
            .range(['#98abc5', '#8a89a6', '#7b6888', '#6b486b', '#a05d56', '#d0743c', '#ff8c00']);

        var xAxis = d3.svg.axis()
            .scale(x0)
            .orient('bottom');

        var yAxis = d3.svg.axis()
            .scale(y)
            .orient('left')
            .tickFormat(d3.format('.2s'));

        var svg = d3.select('.grafica1').append('svg')
            .attr('width', width + margin.left + margin.right)
            .attr('height', height + margin.top + margin.bottom)
          .append('g')
            .attr('transform', 'translate(' + margin.left + ',' + margin.top + ')');

        d3.csv('scripts/data.csv', function(error, data) {
          var ageNames = d3.keys(data[0]).filter(function(key) { return key !== 'State'; });

          data.forEach(function(d) {
            d.ages = ageNames.map(function(name) { return {name: name, value: +d[name]}; });
          });

          x0.domain(data.map(function(d) { return d.State; }));
          x1.domain(ageNames).rangeRoundBands([0, x0.rangeBand()]);
          y.domain([0, d3.max(data, function(d) { return d3.max(d.ages, function(d) { return d.value; }); })]);

          svg.append('g')
              .attr('class', 'x axis')
              .attr('transform', 'translate(0,' + height + ')')
              .call(xAxis);

          svg.append('g')
              .attr('class', 'y axis')
              .call(yAxis)
            .append('text')
              .attr('transform', 'rotate(-90)')
              .attr('y', 6)
              .attr('dy', '.71em')
              .style('text-anchor', 'end')
              .style('font-size', '11px')
              .text('Population');

          var state = svg.selectAll('.state')
              .data(data)
            .enter().append('g')
              .attr('class', 'g')
              .attr('transform', function(d) { return 'translate(' + x0(d.State) + ',0)'; });

          state.selectAll('rect')
              .data(function(d) { return d.ages; })
            .enter().append('rect')
              .attr('width', x1.rangeBand())
              .attr('x', function(d) { return x1(d.name); })
              .attr('y', function(d) { return y(d.value); })
              .attr('height', function(d) { return height - y(d.value); })
              .style('fill', function(d) { return color(d.name); });

          var legend = svg.selectAll('.legend')
              .data(ageNames.slice())
            .enter().append('g')
              .attr('class', 'legend')
              .attr('transform', function(d, i) { return 'translate(0, ' + i * 20 + ')'; });

          legend.append('rect')
              .attr('x', width - 18)
              .attr('width', 18)
              .attr('height', 18)
              .style('fill', color);

          legend.append('text')
              .attr('x', width - 24)
              .attr('y', 9)
              .attr('dy', '.35em')
              .style('text-anchor', 'end')
              .style('font-size', '11px')
              .text(function(d) { return d; });
        });
    }
    return {
        init: init
    };
}());

var piramide = function(){

    var PISOS_PIRAMIDE = ['on1', 'on2', 'on3'];
    function init(obj){
        var txts = obj.find('.txt');
        txts.hover(
            function(){
                var idx = txts.index($(this));
                obj.addClass(PISOS_PIRAMIDE[idx]);
            },
            function(){
                var idx = txts.index($(this));
                obj.removeClass(PISOS_PIRAMIDE[idx]);
            }
        )
        
    }
    return {
        init:init
    };
}();

var calendar = function(){
  function init(){
    var dp1 = new Datepicker('dp1', 'date1', true, true);
   $('#btn_from1').click(function(e) {
      dp1.showDlg();
      e.preventDefault();
      e.stopPropagation();
   });
  }
  return {
    init:init
  };
}();

var calendar2 = function () {
    function init() {
        var dp2 = new Datepicker('dp2', 'date2', true, true);
        $('#btn_from2').click(function (e) {
            dp2.showDlg();
            e.preventDefault();
            e.stopPropagation();
        });
    }
    return {
        init: init
    };
} ();

var calendar3 = function () {
    function init() {
        var dp3 = new Datepicker('dp3', 'date3', true, true);
        $('#btn_from3').click(function (e) {
            dp3.showDlg();
            e.preventDefault();
            e.stopPropagation();
        });
    }
    return {
        init: init
    };
} ();

var calendar4 = function () {
    function init() {
        var dp4 = new Datepicker('dp4', 'date4', true, true);
        $('#btn_from4').click(function (e) {
            dp4.showDlg();
            e.preventDefault();
            e.stopPropagation();
        });
    }
    return {
        init: init
    };
} ();

var calendar5 = function () {
    function init() {
        var dp5 = new Datepicker('dp5', 'date5', true, true);
        $('#btn_from5').click(function (e) {
            dp5.showDlg();
            e.preventDefault();
            e.stopPropagation();
        });
    }
    return {
        init: init
    };
} ();


function funcionesReady() {
    'use strict';

    var texto = {
        selectorCarrusel: '.carrusel',
        selectorModules: '.modules',
        selectorAjustaAltos: '.ajusta-altos',
        selectorTabs: '.tabs',
        selectorFaq: '.faq',
        selectorGrafica: '.grafica',
        selectorPiramide: '.piramide'
    },
        dispositivo = navigator.userAgent,
        esIPad = /iPad/i.test(dispositivo),
        $modules = $(texto.selectorModules),
        $modulesLength = $modules.length > 0,
        $carrusel = $(texto.selectorCarrusel),
        $carruselLength = $carrusel.length > 0,
        $ajustaAltos = $(texto.selectorAjustaAltos),
        $ajustaAltosLength = $ajustaAltos.length > 0,
        $tabs = $(texto.selectorTabs),
        $tabsLength = $tabs.length > 0,
        $faq = $(texto.selectorFaq),
        $faqLength = $faq.length > 0,
        $grafica = $(texto.selectorGrafica),
        $piramide = $(texto.selectorPiramide),
        $graficaLength = $grafica.length > 0,
        $piramideLength = $(piramide).length > 0;


    if (esIPad) {
        if ($modulesLength) {
            ajustesModulesIpad.init($modules);
        }
    }
    if ($carruselLength) {$carrusel.carrusel($carrusel); }
    if ($ajustaAltosLength) {ajustaAltos.init($ajustaAltos); }
    if ($tabsLength) {$tabs.tabs($tabs); }
    if ($faqLength) {faqDesplegables.init($faq); }
    if ($graficaLength) {grafica.init($grafica); }
    if ($piramideLength) {piramide.init($piramide); }
    if ($('.datepicker').length !== 0) { calendar.init(); calendar2.init(); calendar3.init(); calendar4.init(); calendar5.init(); }
    $('#file').customFileInput();


    var menu = $('.menu-horizontal ol ol');
    if(menu.css("height") == "80px"){

        $('#menu-horizontal').css({"height":"130px"});
    }
    else if(menu.css("height") == "40px"){
        $('#menu-horizontal').css({"height":"85px"});
    }
    
    function pantallaAnchoMinimo(){
        var anchoPantalla = 480;
        return $(window).width() <= anchoPantalla;
    }

    $('#cabecera #menu-principal ol li').on('click', function(){
        if(pantallaAnchoMinimo()) {
            $('#cabecera #menu-principal ol li').each(function(i){
                $(this).toggleClass('desplegada');
            });
        }
    });

    $('#cabecera #menu-principal ol li.active a').on('click', function(event){
        if(pantallaAnchoMinimo()) {
            event.preventDefault();
        }
    });

    
}

$(document).on('ready', funcionesReady);
