a [Quark](https://supercollider-quarks.github.io/quarks/) for [SuperCollider](https://supercollider.github.io)

# GUICV

GUI widgets with colour theme and built-in controllers (M-V-C). Also these widgets filter out repeated values i.e. they only redraw when needed, so they are more efficient than the built-in ones.

## Requirements

[SuperCollider](https://supercollider.github.io) version 3.10 or newer running under macOS, Linux or Windows.

## Dependancies

* **QtGUI** - which means it will run on most SuperCollider installs except "_headless_".

## Installation

```supercollider
//install
Quarks.fetchDirectory
Quarks.install("https://github.com/redFrik/GUICV")
//recompile
Quarks.installed.detect{|x| x.name=="GUICV"}.help
```
