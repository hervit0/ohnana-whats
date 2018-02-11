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

## Run service
```
mix escript.build && ./scraper
```

## Run tests suite
```
mix test
```

## Wikipedia API

Find some Wikipedia category nomenclatures (Advanced search > Category):
- https://en.wikipedia.org/w/api.php?action=query&cmlimit=50&cmtitle=Category:21st-century_English_male_actors&format=json&list=categorymembers

Display the content of a category:
- https://en.wikipedia.org/w/index.php?title=Special:Search&profile=advanced&search=&fulltext=1

## Tasks and tech debts on this service

- Enhance the way categories are queries
- Enable wiki list query and list parsing
- Enable a smart triage on the names (alphabetical retrieval for now)

## Performances

```
herve:-> time ./scraper

real	0m1.275s
user	0m0.545s
sys     0m0.379s
```

More than 1 second, that's not great. Will scale it for multiple categories, by spawning proper Elixir processes.
