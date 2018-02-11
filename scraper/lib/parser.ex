defmodule Scraper.Parser do
  def retrieve_names(body) do
    body
    |> get_in(["query", "categorymembers"])
    |> Stream.map(&Map.get(&1, "title"))
    |> Stream.map(&sanitize_title(&1))
  end

  defp sanitize_title(title) do
    Regex.replace(~r/ *\([^)]*\) */, title, "")
  end
end
