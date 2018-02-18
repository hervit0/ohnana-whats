defmodule Http do
  defp host, do: "http://localhost:5050/api/v1/"
  def session_url, do: host() <> "session"
  def game_url, do: host() <> "game"
end
