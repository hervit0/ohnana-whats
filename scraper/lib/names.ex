defmodule Scraper.Names do
  alias Scraper.HttpCaller
  alias Scraper.Parser

  def get_by(category) do
    {:ok, response} = HttpCaller.get(category)

    Parser.retrieve_names(response)
    |> Enum.map(&label(category, &1))
  end

  defp label(category, name) do
    %{"category" => category, "name" => name}
  end
end
