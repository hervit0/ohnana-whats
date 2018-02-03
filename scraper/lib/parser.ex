defmodule Scraper.Parser do
  # Parser should sanitize, e.g "Jean Dujardin (actor)" => "Jean Dujardin"
  def retrieve_names(body) do
    body
    |> get_in(["query", "categorymembers"])
    |> Enum.map(&Map.get(&1, "title"))
  end
end
