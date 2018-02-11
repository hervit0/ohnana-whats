defmodule Scraper.Categories do
  alias Scraper.Names

  def get_names_by_categories(categories) do
    categories
    |> Enum.reduce([], fn category, acc -> acc ++ Names.get_by(category) end)
  end
end
