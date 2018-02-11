defmodule Scraper.Names do
  alias Scraper.HttpCaller
  alias Scraper.Parser

  def get_by({category_label, category}) do
    {:ok, response} = HttpCaller.get(category)

    response
    |> Parser.retrieve_names()
    |> Stream.map(&label(category_label, &1))
    |> Enum.to_list()
  end

  defp label(category_label, name) do
    %{"category" => category_label, "name" => name}
  end
end
