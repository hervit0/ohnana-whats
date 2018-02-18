defmodule Game do
  defp game_url, do: Http.game_url()

  def post_response(headers) do
    HTTPoison.post(game_url(), "", headers)
  end

  def get_response(game_id, headers) do
    url = game_url() <> "/" <> game_id
    HTTPoison.get(url, headers)
  end

  def set_headers(authorization, session_id) do
    [
      {"Accept", "application/json"},
      {"Content-Type", "application/json"},
      {"Authorization", authorization},
      {"Session-Id", session_id}
    ]
  end

  def set_authorized_headers(session_id) do
    set_headers(Ohnana.correct_authorization(), session_id)
  end

  def set_unauthorized_headers(session_id) do
    set_headers(Ohnana.incorrect_authorization(), session_id)
  end

  def get_game_id(session_id) do
    {:ok, %{body: body}} =
      session_id
      |> set_authorized_headers()
      |> __MODULE__.post_response()

    Poison.decode!(body)["data"]["id"]
  end
end
