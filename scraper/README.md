# Scraper

## Acknowledgments

Mainly inspired from [here](https://hackernoon.com/elixir-console-application-with-json-parsing-lets-print-to-console-b701abf1cb14).

## Installation

### Requirements

- ~Rust compiler: `curl https://sh.rustup.rs -sSf | sh` (don't forget to update the PATH after the installation: `source $HOME/.cargo/env`)~
- Elixir `1.6.0`

~Nota: `Rust` is required in order to install this dependency `meeseeks_html5ever`, which is a dependency of `meeseeks`.~

### Dependencies

- Elixir dependencies: `mix deps.get`

## Run

```
mix escript.build && ./scraper
```

## Tasks and tech debts on this service

- Write tests (proof of concept for now, don't judge me)
- Sanitize inputs
- Enhance the way categories are queries
- Enable a smart triage on the names (alphabetical retrieval for now)

## Performances

```
herve:-> time ./scraper

real	0m1.275s
user	0m0.545s
sys	0m0.379s
```

Not great, will scale it for multiple categories, by spawning proper Elixir processes.
