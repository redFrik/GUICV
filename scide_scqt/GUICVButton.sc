GUICVButton : AbstractGUICVView {

	string_ {|str|
		str= str.asString;
		this.states_(this.states.flop.put(0, str!this.states.size).flop);
	}

	prCreateView {|parent, bounds|
		var skin= GUI.skins.guiCV;
		^GUICV.button(parent, bounds)
		.states= [
			["", skin.fontColor, skin.foreground],
			["", skin.background, skin.highlight]
		]
	}
}

GUICVButtonPlay : GUICVButton {

	var container;

	asView {^container}

	string_ {|str| }

	prCreateView {|parent, bounds|
		var skin= GUI.skins.guiCV;
		var lastVal, refresher;

		var sl= StackLayout().mode_(\stackAll);

		var button= super.prCreateView(parent, bounds);

		var userView= UserView(parent, bounds)
		.acceptsMouse_(false)
		.canFocus_(false)
		.drawFunc_(this.prDrawFunc(skin));

		sl.add(userView);
		sl.add(button);
		container= View(parent, bounds).layout_(sl)
		.background_(skin.background)
		.resizeToBounds(bounds ?? {Size(skin.buttonHeight, skin.buttonHeight)});

		refresher= {|cv, val|
			if(val!=lastVal, {
				userView.refresh;
				lastVal= val;
			});
		};
		cv.addAction(refresher);
		userView.onClose_({cv.removeAction(refresher)});

		^button
	}

	prDrawFunc {|skin|
		^{|usr|
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			var dw= w-w.min(h)*0.5;
			var dh= h-w.min(h)*0.5;
			var mx= GUI.skins.guiCV.margin.x;
			var my= GUI.skins.guiCV.margin.y;
			Pen.moveTo(Point(dw+mx, dh+my));
			if(view.value==0, {
				Pen.fillColor= skin.fontColor;
				Pen.lineTo(Point(w-dw-mx, h*0.5));
			}, {
				Pen.fillColor= skin.foreground;
				Pen.lineTo(Point(w-dw-mx, dh+my));
				Pen.lineTo(Point(w-dw-mx, h-dh-my));
			});
			Pen.lineTo(Point(dw+mx, h-dh-my));
			Pen.lineTo(Point(dw+mx, dh+my));
			Pen.fill;
		}
	}
}

GUICVButtonLoop : GUICVButtonPlay {

	var <>rotation= 0.33pi;

	refresh {container.refresh}

	prDrawFunc {|skin|
		^{|usr|
			var w= usr.bounds.width;
			var h= usr.bounds.height;
			var m= GUI.skins.guiCV.margin;
			var rad= w.min(h)*0.5-m.x.max(m.y);
			var arrow= rad*0.25;
			var th= arrow*sin(1);
			var a1= Point(rad-arrow, 0-th);
			var a2= Point(arrow-rad, th);

			Pen.translate(w*0.5, h*0.5);
			Pen.rotate(rotation);
			Pen.addArc(Point(0, 0), rad-arrow, 0, 0.75pi);
			Pen.addArc(Point(0, 0), rad-arrow, pi, 0.75pi);
			Pen.line(a1, Point(rad, arrow-th));
			Pen.line(a1, Point(rad-arrow, arrow-th));
			Pen.line(a1, Point(rad-(arrow*2), arrow-th));
			Pen.line(a2, Point(0-rad, th-arrow));
			Pen.line(a2, Point(arrow-rad, th-arrow));
			Pen.line(a2, Point((arrow*2)-rad, th-arrow));

			if(view.value==0, {
				Pen.strokeColor= skin.fontColor;
			}, {
				Pen.strokeColor= skin.foreground;
			});
			Pen.stroke;
		}
	}
}
