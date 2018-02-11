defmodule Scraper do
  alias Scraper.Categories

  @output_file "output/cards.json"
  @categories %{
    "French actresses" => "21st-century_French_actresses",
    "French actors" => "21st-century_French_male_actors",
    "British actresses" => "21st-century_English_actresses",
    "British actors" => "21st-century_English_male_actors",
    "British rock singers" => "English_rock_singers"
  }

  def main(_args \\ []) do
    HTTPoison.start()

    @categories
    |> Categories.get_names_by_categories()
    |> Poison.encode!()
    |> output()
  end

  defp output(content) do
    {:ok, file} = File.open(@output_file, [:write])
    IO.binwrite(file, content)
    File.close(file)
  end
end
