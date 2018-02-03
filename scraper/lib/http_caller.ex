defmodule Scraper.HttpCaller do
  @base_url "https://en.wikipedia.org/w/api.php?"

  def get(category) do
    url(category)
    |> HTTPoison.get()
    |> handle_json()
  end

  defp url(category) do
    query_params = %{
      "action" => "query",
      "list" => "categorymembers",
      "cmtitle" => "Category:" <> category,
      "cmlimit" => "50",
      "format" => "json"
    }

    @base_url <> build_query_string(query_params)
  end

  defp build_query_string(params) do
    params
    |> Enum.map(fn {param, value} -> param <> "=" <> value end)
    |> Enum.join("&")
  end

  defp handle_json({:ok, %{status_code: 200, body: body}}) do
    {:ok, Poison.Parser.parse!(body)}
  end

  defp handle_json({_, %{status_code: _, body: _}}) do
    {:error, "Shet happened"}
  end
end
