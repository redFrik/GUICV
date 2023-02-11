a [Quark](https://supercollider-quarks.github.io/quarks/) for [SuperCollider](https://supercollider.github.io)

# GUICV

Some GUI widgets with a colour theme and built-in controllers (M-V-C).

These widgets are also efficient as they filter out repeated values and only redraw when needed.

Mainly written for use in the [GFX](https://github.com/redFrik/GFX) quark. Based on my older redGUI in the [redSys](https://github.com/redFrik/redSys) quark. redGUI was, in turn, loosely based on Ron Kuivila's [Conductor](https://github.com/supercollider-quarks/Conductor) quark.

### Basic example:

```supercollider
//code <-> gui
(
a= GUICVKnob().front;
)
a.value;  //click the knob and this will be different
a.value= 1.0.rand;  //set the knob's value from code
```

## Requirements

[SuperCollider](https://supercollider.github.io) version 3.9 or newer running under macOS, Linux or Windows.

## Dependancies

* **QtGUI** - i.e. will run on most SuperCollider installs.

The exception is the CV class. It can be used without QtGUI.

## Installation

```supercollider
//install
Quarks.install("https://github.com/redFrik/GUICV")
//recompile
"GUICV".help
```
