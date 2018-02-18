defmodule Session do
  defp session_url, do: Http.session_url()

  def post_response(headers) do
    HTTPoison.post(session_url(), "", headers)
  end

  def get_response(headers) do
    HTTPoison.get(session_url(), headers)
  end

  def set_headers(authorization, session_id) do
    [
      {"Accept", "application/json"},
      {"Authorization", authorization},
      {"Session-Id", session_id}
    ]
  end

  def set_authorized_headers() do
    set_headers(Ohnana.correct_authorization(), "")
  end

  def set_authorized_headers(session_id) do
    set_headers(Ohnana.correct_authorization(), session_id)
  end

  def set_unauthorized_headers() do
    set_headers(Ohnana.incorrect_authorization(), "")
  end

  def set_unauthorized_headers(session_id) do
    set_headers(Ohnana.incorrect_authorization(), session_id)
  end

  def get_session_id() do
    {:ok, %{body: body}} =
      ""
      |> set_authorized_headers()
      |> __MODULE__.post_response()

    Poison.decode!(body)["data"]["id"]
  end
end
