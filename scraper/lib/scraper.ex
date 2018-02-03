defmodule Scraper do
  alias Scraper.Names
  @output_file "output/cards.json"

  def main(_args \\ []) do
    HTTPoison.start()

    # Wikipedia nomenclatures (Advanced search > Category)
    categories = [
      "21st-century_French_actresses",
      "21st-century_French_male_actors",
      "21st-century_English_actresses",
      "21st-century_English_male_actors"
    ]

    categories
    |> Enum.reduce([], fn category, acc -> acc ++ Names.get_by(category) end)
    |> Poison.encode!()
    |> output()
  end

  defp output(content) do
    {:ok, file} = File.open(@output_file, [:write])
    IO.binwrite(file, content)
    File.close(file)
  end
end
